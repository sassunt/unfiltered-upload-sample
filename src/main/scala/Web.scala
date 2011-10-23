package com.sassunt

import scala.io.Source
import unfiltered.request._
import unfiltered.response._
import util.Properties

class Temperature extends unfiltered.filter.Plan {
  def intent = {
    case req @ GET(_) => Ok ~> view(None, Nil)
    case requ @ POST(Path(_) & MultiPart(req)) => MultiPartParams.Streamed(req).files("file") match {
      case Seq(file, _*) if !file.name.isEmpty  =>
        view(Some(file.name), file.stream(t => Source.fromInputStream(t).getLines.toList))
      case f => view(None, Nil)
    }
  }

  def view(temp: Option[String], text: List[String]) = Html{
    <html>
      <body>
        <form method="POST" enctype="multipart/form-data">
          <input type="file" value="" name="file" />
          <input type="submit" />
        </form>
        { temp.map(t =>
          <div>
            <h1>File Name: {t}</h1>
            <pre>
              {text.zipWithIndex.map{case (line,index) => <div>{"%4d: %s".format(index + 1,  line)}</div>}}
            </pre>
          </div>
          ).toSeq }
      </body>
    </html>
  }
}

object Web {
  def main(args: Array[String]) {
    val port = Properties.envOrElse("PORT", "8080").toInt
    unfiltered.jetty.Http(port).filter(new Temperature).run
  }
}

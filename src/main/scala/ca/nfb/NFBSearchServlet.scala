package ca.nfb

import org.scalatra._
import scalate.ScalateSupport

// JSON-related libraries
import org.json4s.{DefaultFormats, Formats}

// JSON handling support from Scalatra
import org.scalatra.json._

import uk.co.bigbeeconsultants.http.HttpClient
import uk.co.bigbeeconsultants.http.response.Response
import java.net.URL

class NFBSearchServlet extends NfbsearchscalatraStack with JacksonJsonSupport {

  // Sets up automatic case class to JSON output serialization, required by
  // the JValueResult trait.
  protected implicit val jsonFormats: Formats = DefaultFormats

  // Before every action runs, set the content type to be in JSON format.
/*  before() {
    contentType = formats("json")
  }
*/
  get("/") {
    "nope, not here! try this: <a href=\"/search?q=came\">/search</a>"
  }

  get("/search") {
    contentType = formats("json")
    val q:String = params("q")
    val httpClient = new HttpClient
    val response: Response = httpClient.get(new URL("http://vmdev.nfb.ca:9200/nfb_films/films/_search?q=" + q))
    response.body.asString
  }
  
}

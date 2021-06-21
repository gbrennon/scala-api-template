package io.conduktor.api.http.v1

import sttp.client3._
import sttp.client3.httpclient.zio.HttpClientZioBackend
import sttp.model.StatusCode
import zio.test.Assertion._
import zio.test.environment.TestEnvironment
import zio.test.{DefaultRunnableSpec, ZSpec, _}

object PostRoutesSpec extends DefaultRunnableSpec {

  override def spec: ZSpec[TestEnvironment, Any] = suite("/v1/posts")(
    testM("POST / should return 200") {
      val payload = """{
        | "title": "my test",
        | "content": "blabla"
        | }""".stripMargin
      for {
        client <- HttpClientZioBackend()
        response <- basicRequest.body(payload).post(uri"/v1/posts").send(client)
      } yield assert(response.code)(equalTo(StatusCode.Ok))

    }
  )

}

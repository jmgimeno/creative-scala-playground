import doodle.core._
import doodle.image._
import doodle.image.syntax.all._
import doodle.image.syntax.core._
import doodle.java2d._
import doodle.reactor._
import scala.concurrent.duration._
import cats.effect.unsafe.implicits.global

object TemplateExamples:

  @main def image(): Unit =
    val image =
      Image
        .circle(100)
        .fillColor(Color.red)
        .on(Image.circle(200).fillColor(Color.aquamarine))
        .on(Image.circle(300).fillColor(Color.steelBlue))
    image.draw()

  @main def animation(): Unit =
    val animation =
      Reactor
        .init(-200)
        .withOnTick(x => x + 1)
        .withStop(x => x > 200)
        .withTickRate(20.millis)
        .withRender { x =>
          val y = x.degrees.sin * 200
          val planet = Image.circle(20.0).noStroke.fillColor(Color.seaGreen)
          val moon = Image
            .circle(5.0)
            .noStroke
            .fillColor(Color.slateGray)
            .at((x * 10).degrees.cos * 50, (x * 10).degrees.sin * 50)
          moon.on(planet).at(x, y)
        }
    val frame = Frame.size(600, 600)
    animation.run(frame)


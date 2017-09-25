package cli

import java.io.File

case class CliConfig(
    size: Int = 8,
    output: Option[File] = None
)

object CliConfig {

  val parser = new scopt.OptionParser[CliConfig]("Discretas III") {
    head("The n rooks problem, Discretas III")
      .text("Tarea 1 de discretas III")

    opt[Int]('s', "size")
      .valueName("<size>")
      .text("Tamaño del tablero de ajedrez")
      .validate(s => if (s > 0) success else failure("No se permite números negativos o 0"))
      .action((s, c) => c.copy(size = s))


    opt[File]('o', "output")
      .valueName("<output>")
      .text("Archivo donde guardar la salida del programa")
      .action((f, c) => c.copy(output = Some(f)))

  }

}
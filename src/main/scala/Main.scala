import java.nio.file.Files

import ports.cli.CliConfig

object Main {
  def main(args: Array[String]): Unit = {
    CliConfig.parser.parse(args, CliConfig()) match {
      case Some(config) => run(config)
      case None => Unit
    }
  }

  def run(config: CliConfig): Unit = {
    val permutations = RooksProblem.show(RooksProblem.findPermutations(config.size))

    config.output match {
      case Some(outfile) => Files.write(outfile.toPath, permutations.getBytes("UTF-8"))
      case None => println(permutations)
    }
  }
}
import scala.io.Source
import java.util.Scanner
import scala.collection.mutable
import scala.collection.mutable.Set

object fileScope {
    def main(args: Array[String]): Unit = {
        if (args.length == 0) Help()
        val argsList = args.toList
        argsList.head match {
            case "--linesFile-words" | "-lf" => Words(argsList(1))
            case "--linesInFile" | "-f" => Lines(argsList(1))
            case "--censor" | "-c" => Censor(argsList)
            case "--help" | "-h" => Help()
            case _ => Help()
        }
    }

    def Censor(aList : List[String]): Unit = {
        var cenSet = mutable.Set[String]()
        for (i <- 2 until aList.size) {
            cenSet += aList(i)
        }
        var wordsFile = 0
        for (line <- Source.fromFile(aList(1)).getLines()) {
            val arrWords : Array[String] = line.split("\\s+")
            var correctWordsLine = 0
            for (word <- arrWords) {
                if (!cenSet.contains(word))
                    correctWordsLine += 1
            }
            wordsFile += correctWordsLine
            println(correctWordsLine.toString + " " + line)
        }
        println("\n" + wordsFile)
    }

    def Count(arg : String): Unit = {
        for (line <- Source.fromFile(arg).getLines())
            println(line.length.toString + " " + line)
    }

    def File(arg : String) : Unit = {
        var count = 0
        for (line <- Source.fromFile(arg).getLines()) {
            count += line.length
        }
        println(count.toString)
    }

    def Help(): Unit = {
        println("\nscala FileScope [--method] [file]\n")
        println("Methods:\n--linesFile-words (-lf)\n--linesInFile (-f)\n" +
                "--censor (-c)\n--help (-h)")
    }

    def Lines(arg : String): Unit = {
        println(Source.fromFile(arg).getLines().length)
    }

    def Words(arg : String): Unit = {
        var wordsFile = 0
        for (line <- Source.fromFile(arg).getLines()) {
            val arrWords : Array[String] = line.split("\\s+")
            wordsFile += arrWords.length
            println(arrWords.length.toString + " " + line)
        }
        println("\n" + wordsFile)
    }

}
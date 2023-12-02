import java.io.File

class CubeCounter { 
   static void main(String[] args) {
      Set<Integer> validGames = new HashSet<>()
      new File("../../../inputs/day-2/input.txt").eachLine {
         line -> CubeCounter.parseLine(line, validGames)
      }
      int sum = 0
      for (int gameNum : validGames) {
         sum += gameNum
      }
      println("Total: " + sum)
   }   
   private static void parseLine(String line, Set<Integer> validGames) {
      String[] slices = line.split(':')
      String[] gameSlice = slices[0].split(' ')
      int gameNum = gameSlice[1].toInteger()

      String[] gameGrabs = slices[1].split(';')
      parseGame(gameGrabs, gameNum, validGames)
      return
   }
   private static void parseGame(String[] gameGrabs, int gameNum, Set<Integer> validGames) {
      for(String grab: gameGrabs) {
         boolean validGrab = parseGrab(grab)
         if (!validGrab) {
            return
         }
      }
      // If all grabs valid
      validGames.add(gameNum)
      return
   }
   private static boolean parseGrab(String grab) {
      String[] takes = grab.split(',')
      for (String take : takes) {
         String[] components = take.split(' ')
         int count = components[1].toInteger()
         String color = components[2]
         if(!validCount(color, count)) {
            return false
         }
      }
      return true
   }
   private static boolean validCount(String color, int count) {
      switch(color) {
         case "red":
            count <= 12
            break
         case "green":
            count <= 13
            break
         case "blue":
            count <= 14
            break
         default:
            false
            break
      }
   }

}
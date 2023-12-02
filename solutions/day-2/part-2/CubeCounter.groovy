import java.io.File

class CubeCounter { 
   static void main(String[] args) {
      int[] gamePowers = new int[100]
      for (int index = 0; index < gamePowers.length; index++) {
         gamePowers[index] = 1
      }
      new File("../../../inputs/day-2/input.txt").eachLine {
         line -> CubeCounter.parseLine(line, gamePowers)
      }
      int sum = 0
      for (int gamePower : gamePowers) {
         sum += gamePower
      }
      println("Total: " + sum)
   }   
   private static void parseLine(String line, int[] gamePowers) {
      String[] slices = line.split(':')
      String[] secondSlice = slices[0].split(' ')
      int gameNum = secondSlice[1].toInteger()

      String[] gameGrabs = slices[1].split(';')

      parseGame(gameGrabs, gameNum, gamePowers)
      return
   }
   private static void parseGame(String[] gameGrabs, int gameNum, int[] gamePowers) {
      int[] maxColors = [1, 1, 1]
      for(String grab: gameGrabs) {
         parseGrab(grab, gameNum, maxColors)
      }
      maxColors.each{ colorCount ->
         gamePowers[gameNum-1] *= colorCount;
      }
      return
   }
   private static void parseGrab(String grab, int gameNum, int[] maxColors) {
      String[] takes = grab.split(',')
      for (String take : takes) {
         String[] components = take.split(' ')
         int count = components[1].toInteger()
         String color = components[2]
         int index = -1
         switch(color) {
            case "red":
               index = 0
               break
            case "green":
               index = 1
               break
            case "blue":
               index = 2
               break
            default:
               // An error occurred
               index = -1
               break
         }
         maxColors[index] = Math.max(maxColors[index], count)
      }
      return
   }
}
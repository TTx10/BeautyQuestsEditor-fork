package EcoMineVN.BeautyQuestsEditor.Utils;

import EcoMineVN.BeautyQuestsEditor.Init;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Random;

public class Utils {
  public static void writeLog(String... messages) {
    byte b;
    int i;
    String[] arrayOfString;
    for (i = (arrayOfString = messages).length, b = 0; b < i; ) {
      String message = arrayOfString[b];
      Init.console.sendMessage(formatText("&f[&cEcoMineVN_PT&f] " + message));
      b++;
    } 
  }
  
  public static String formatText(String text) {
    return text.replace("&", "");
  }
  
  public static String MathToString(String s) {
    ScriptEngineManager mgr = new ScriptEngineManager();
    ScriptEngine engine = mgr.getEngineByName("JavaScript");
    try {
      Object ketqua = engine.eval(s);
      return String.valueOf(ketqua);
    } catch (ScriptException scriptException) {
      return "0";
    } 
  }
  
  public static boolean tiLe(double tiLe) {
    double rate = tiLe * 100.0D;
    int random = (new Random()).nextInt(10000);
    return (random < rate);
  }
  
  public static int getRandom(int min, int max) {
    if (min >= max)
      throw new IllegalArgumentException("max must be greater than min"); 
    Random r = new Random();
    return r.nextInt(max - min + 1) + min;
  }
}

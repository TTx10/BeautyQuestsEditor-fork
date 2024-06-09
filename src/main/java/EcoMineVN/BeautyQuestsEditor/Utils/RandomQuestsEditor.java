package EcoMineVN.BeautyQuestsEditor.Utils;

import EcoMineVN.BeautyQuestsEditor.Quests.QuestsEditor;

public class RandomQuestsEditor {
  private final QuestsEditor questsEditor;
  
  private final int rate;
  
  public RandomQuestsEditor(QuestsEditor questsEditor, int rate) {
    this.questsEditor = questsEditor;
    this.rate = rate;
  }
  
  public int getRate() {
    return this.rate;
  }
  
  public QuestsEditor getQuestsEditor() {
    return this.questsEditor;
  }
}

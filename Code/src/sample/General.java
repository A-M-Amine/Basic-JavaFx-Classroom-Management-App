package sample;

public class General {

    String var1;
    String var2;
    String var3;
    String var4;
    String var5;

    public General(String var1) {
        this.var1 = var1;
    }

    public General(String var1, String var2) {
        this.var1 = var1;
        this.var2 = var2;
    }

    public General(String var1, String var2, String var3, String var4, String var5) {
        this.var1 = var1;
        this.var2 = var2;
        this.var3 = var3;
        this.var4 = var4;
        this.var5 = var5;
    }

    public General(String var1, String var2, String var3, String var4) {
        this.var1 = var1;
        this.var2 = var2;
        this.var3 = var3;
        this.var4 = var4;
    }

    public General(String var1, String var2, String var3) {
        this.var1 = var1;
        this.var2 = var2;
        this.var3 = var3;
    }

    public String getVar1() {
        return var1;
    }

    public void setVar1(String var1) {
        this.var1 = var1;
    }

    public String getVar2() {
        return var2;
    }

    public void setVar2(String var2) {
        this.var2 = var2;
    }

    public String getVar3() {
        return var3;
    }

    public void setVar3(String var3) {
        this.var3 = var3;
    }

    public String getVar4() {
        return var4;
    }

    public void setVar4(String var4) {
        this.var4 = var4;
    }

    public String getVar5() {
        return var5;
    }

    public void setVar5(String var5) {
        this.var5 = var5;
    }
}

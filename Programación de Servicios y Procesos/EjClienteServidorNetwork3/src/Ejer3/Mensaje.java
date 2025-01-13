package Ejer3;

public class Mensaje {
private String msg;
public Mensaje(String x)
{this.msg=x;}

public synchronized void setMsg(String x)
{
this.msg=x;	
}

public synchronized String getMsg()
{
return msg;	
}

}

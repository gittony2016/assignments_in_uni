package edu.comp9321.ass2;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;



public class IpTimeStamp {
	private SimpleDateFormat sim=null;//用来获取时间
    private String ip=null;
    public IpTimeStamp(){
    }
    public IpTimeStamp(String ip){
        this.ip=ip;
    }
    public String getIpTimeRand(){
        StringBuffer sbf=new StringBuffer();
        if(this.ip!=null){
            String a[]=this.ip.split("\\.");                //根据点来拆分ip地址，但点要转义
            for(int i=0;i<a.length;i++){
                sbf.append(this.addZero(a[i], 3));            //调用补零的方法，每块ip不足三位的自动补足到三位
            }
            sbf.append(this.getTimeStamp());                //用this来调用外部的方法
            Random random=new Random();                        //要产生随机数
            for(int i=0;i<3;i++){                            //产生三位随机数
                sbf.append(random.nextInt(10));                //每位随机数都不超过10
            }
        }
        return sbf.toString();
    }
    @SuppressWarnings("unused")
    private String getDate(){                                //关于日期与时间的实现
        this.sim=new SimpleDateFormat("yyyy-mm-dd hh:mm:ss.SSS");
        return this.sim.format(new Date());
    }
    private String getTimeStamp(){                            //返回时间戳
        this.sim=new SimpleDateFormat("yyyymmddhhmmssSSS");
        return this.sim.format(new Date());
    }
    private String addZero(String str,int len){                //自动补零的方法，参数为指定的字符串和长度
        StringBuffer s=new StringBuffer();
        s.append(str);
        while(s.length()<len){
            s.insert(0,"0");                                //在零的位置上进行补零操作
        }
        return s.toString();
    }
}

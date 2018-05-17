package com.rtw.Unit;

import com.rtw.Unit.DB.SqlExecuter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangrun
 * @version 0.1
 */
public class MailAdressUnit {
    /**
     *
     * @param nickName
     * @param packetAdress
     * @return
     */
    private SqlExecuter sqlExecuter = new SqlExecuter();
    public String addAdressMap(String nickName ,String packetAdress){
        String sql = "insert into MailAddress values('"+nickName+"','"+packetAdress+"');";
        System.out.println(sql);
        if(sqlExecuter.insert(sql)){

            return "昵称："+nickName+"钱包地址："+packetAdress+"入库成功";
        }
        else {
            return "昵称："+nickName+"钱包地址："+packetAdress+"入库失败";
        }

    }
    public Map<String ,String> getAddressMap(){
        Map<String,String> name_addressMap = new HashMap<String, String>();
        String sql = "select * from MailAddress";
        ResultSet resultSet = sqlExecuter.selectSql(sql);
        String name="";
        String address = "";
        try {
            while (resultSet.next()){
                name = resultSet.getString(1);
                address = resultSet.getString(2);
                name_addressMap.put(name,address);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name_addressMap;

    }

    public static void main(String[] args) {
        for (Map.Entry<String,String> entry:new MailAdressUnit().getAddressMap().entrySet()){
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }
}

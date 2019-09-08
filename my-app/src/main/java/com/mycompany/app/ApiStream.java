package com.mycompany.app;

import java.util.*;
import com.mycompany.app.domainmodel.*;

public class ApiStream 
{
    public void doTask()
    {
        List<Obj1> ObjList = new ArrayList<Obj1>();
        ObjList.add(new Obj1("1"));
        ObjList.add(new Obj1("2"));
        ObjList.add(new Obj1("3"));
        ObjList.add(new Obj1("4"));

        System.out.println(ObjList.size());
    }
}
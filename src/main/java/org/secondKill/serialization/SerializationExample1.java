package org.secondKill.serialization;

import org.secondKill.entity.Seckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

public class SerializationExample1 {

    private static Logger logger = (Logger) LoggerFactory.getLogger(SerializationExample1.class);

    public static void main(String [] args)
    {
        Seckill e = new Seckill(1, "name1");
        e.setCreateDate(new Date());
        e.setNumber(100);
        e.setEndTime(new Date());
        e.setEndTime(new Date());
        try
        {
            FileOutputStream fileOut =
                    new FileOutputStream("employee.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(e);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in /tmp/employee.ser");
        }catch(IOException i)
        {
            logger.error(String.valueOf(i));
        }
    }
}

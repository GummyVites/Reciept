package com.example.kevinlee.buttonboy;

        import android.content.Context;

        import java.io.ObjectInputStream;
        import java.io.ObjectOutputStream;
        import java.io.Serializable;
        import java.util.List;

public class receipt implements Serializable {
    public String name;
    public List<String> itemNames;
    public List<Float> itemPrices;


    public receipt load (Context ctx) {
        ObjectInputStream in;
        String filename = "receipt";
        receipt temp =null;
        try {
            in = new ObjectInputStream(ctx.openFileInput(filename));
            temp = (receipt) in.readObject();
            in.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

    public void saveReciept (Context ctx) {
        ObjectOutputStream out;

        String filename = "receipt";
        try {
            out = new ObjectOutputStream(ctx.openFileOutput(filename, Context.MODE_PRIVATE));
            out.writeObject(this);
            out.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package Model;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JsonExporter implements Exporter {

    public void export(int id) {


        List<Bilet> biletEntityList = BiletDAO.selectAll(id);
        Gson gson= new Gson();

        try(FileWriter writer=new FileWriter("bileteSpectacol"+id+".json")){

            gson.toJson(biletEntityList,writer);

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

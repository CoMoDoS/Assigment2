package Model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvExporter implements Exporter {
    @Override
    public void export(int id) {


        List<Bilet> biletEntityList = BiletDAO.selectAll(id);


        try(FileWriter writer=new FileWriter("bileteSpectacol"+id+".csv")){

            writer.write("id,idSpectacol,numar,rand\n");
            for( Bilet biletEntity:biletEntityList)
            {
                writer.write(biletEntity.getId()+","+biletEntity.getSpectacol_id()+","+biletEntity.getNumar()+","+biletEntity.getRand()+"\n");
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
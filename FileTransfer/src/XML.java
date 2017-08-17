
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class XML {

    public static void main(String[] args) {
    	File file = new File("C:/Users/lilyi/Documents/选题数据.xls");
        readExcelAddXML(file,"C:/Users/lilyi/Documents/");
	}
    
    public static void readExcelAddXML(File file,String path){
        OutputStreamWriter pw = null;
        try {
            List<Project> list = readExcelFileOutList(file);
            File file1 = new File(path,"data.xml");
            if (!file1.exists()) {
                file1.createNewFile();
            }
            pw = new OutputStreamWriter(new FileOutputStream(file1),"utf-8");
            
            pw.write("<docs>"+"\n");
            pw.write("<lst>"+"\n");
            pw.write("<num>"+list.size()+"</num>"+"\n");
            pw.write("</lst>"+"\n");
            for(int i=0;i <list.size();i++){
                pw.write("<doc>"+"\n");
                pw.write("<project>"+list.get(i).getProject()+"</project>"+"\n");
                pw.write("<id>"+list.get(i).getId()+"</id>"+"\n");
                pw.write("<name>"+list.get(i).getName()+"</name>"+"\n");
                pw.write("<classname>"+list.get(i).getClassname()+"</classname>"+"\n");
                pw.write("<monitorid>"+list.get(i).getMonitorid()+"</monitorid>"+"\n");
                pw.write("<monitorname>"+list.get(i).getMonitorname()+"</monitorname>"+"\n");
                pw.write("</doc>");
                pw.flush();
            }
            pw.write("\n"+"</docs>");
            pw.flush();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                pw.close();//关闭流
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    private static List<Project> readExcelFileOutList(File file) {  
        List<Project> list = new ArrayList<Project>();
        InputStream is = null;
        try{  
           is = new FileInputStream(file);  
           Workbook rwb = Workbook.getWorkbook(is); 
           jxl.Sheet rs = rwb.getSheet(0);   
           int num_row = rs.getRows();//得到行数  
           int num_column=rs.getColumns();//得到列数
           
           System.out.println("行:"+num_row+"列:"+num_column);
           for(int j=1;j < num_row ;j++){  
               Cell[] cell  = rs.getRow(j);//得到第j行的所有值  
                 
               for(int column_index=0;column_index<num_column;column_index++){  
                   String project = cell[column_index++].getContents();
                   String id= cell[column_index++].getContents();
                   String name = cell[column_index++].getContents();
                   String classname = cell[column_index++].getContents();
                   String monitorid= cell[column_index++].getContents();
                   String monitorname = cell[column_index++].getContents();
                   list.add(new Project(project, id, name, classname, monitorid, monitorname));
               }  
            }  
        } catch(Exception ex) {
            ex.printStackTrace();  
        } finally {
            try {
                if(is != null) is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
   } 
    
}
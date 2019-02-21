package swg;

import java.io.*;
import java.nio.ByteBuffer;

public class APTFile {
    private File inputFile;
    private byte[] name;
    private byte[] version = new byte[4];

    private File outputFile;

    public APTFile(String fileName){
        inputFile = new File(fileName);
        outputFile = new File("out/" + inputFile.getName());
    }

    public void read(){
        try{
            parse();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void write() throws IOException {
        FileOutputStream fos = new FileOutputStream(outputFile.getName());
        try{
            int formLength = name.length + 24;
            fos.write("FORM".getBytes());
            fos.write(ByteBuffer.allocate(4).putInt(formLength).array());
            fos.write("APT ".getBytes());
            fos.write("FORM".getBytes());
            fos.write(ByteBuffer.allocate(4).putInt(name.length + 12).array());
            fos.write(version);
            fos.write("NAME".getBytes());
            fos.write(ByteBuffer.allocate(4).putInt(name.length).array());
            fos.write(name);
            fos.write('\0');
        }
        catch(Exception e){
            System.out.println("ERROR: " + e.getMessage());
        }
        finally {
            fos.close();
        }
    }

    private void parse() throws IOException {
        FileInputStream fis = null;
        BufferedInputStream bi = null;
        try {
            byte[] FORM = new byte[4];
            byte[] APTFORM = new byte[8];

            byte[] NAMETag = new byte[4];
            byte[] nameByteSize = new byte[4];

            fis = new FileInputStream(inputFile);
            bi = new BufferedInputStream(fis);
            // work our way to the data.
            bi.read(FORM);
            bi.read(new byte[4]);  // length of form
            bi.read(APTFORM);
            bi.read(new byte[4]);
            bi.read(version);
            bi.read(NAMETag);
            bi.read(nameByteSize);

            // get the length of the path to the LOD or SSA
            int nameLength = ByteBuffer.wrap(nameByteSize).getInt();

            // make a buffer to hold the info
            name = new byte[nameLength];

            // read in the number of bytes that the path occupies
            bi.read(name);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            if(bi != null){
                bi.close();
            }
            if(fis != null){
                fis.close();
            }
        }
    }

    public String getFullName() {
        return new String(name);
    }

    public void setFullName(String val) {
        name = val.getBytes();
    }

    public int getVersion() {
        return Integer.parseInt(new String(version));
    }

    public void setVersion(int val) {
        version = ByteBuffer.allocate(4).putInt(val).array();
    }

    public String getFileName() {
        return getFullName().substring(getFullName().lastIndexOf("/") + 1);
    }

    public String getPathName() {
        return getFullName().replace(getFileName(), "");
    }
}

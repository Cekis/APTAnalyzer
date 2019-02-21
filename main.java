import swg.APTFile;
import swg.LODFile;
import swg.SSAFile;

import java.io.File;
import java.io.IOException;

public class main {
    private static String clientDataRoot = "E:/Games/SWG-Extracted/";
    public static void main(String[] args)
    {
        if(args != null){
            switch(args.length){
                case 0:
                    File f = new File(clientDataRoot + "/appearance");
                    for(File file : f.listFiles()){
                        if(!file.getName().contains(".apt")) continue;
                        APTFile aptFile = new APTFile(clientDataRoot + "appearance/" + file.getName());
                        aptFile.read();
                        if(aptFile.getFullName().contains(".lod")){
                            try {
                                SSAFile ssaFile = new SSAFile(aptFile.getFullName());
                                String ssaFileName = "appearance/" + file.getName().replace(".apt", ".ssa");

                                // first, see if we have an SSA file that we can use
                                File ssaFileCheck = new File(clientDataRoot + ssaFileName);

                                // if it doesn't exist, try to create it from an LOD file.
                                if(!ssaFileCheck.exists()) {
                                    // try to create the SSA file from the LOD file
                                    LODFile lodFile = new LODFile(clientDataRoot + aptFile.getFullName());
                                    if (!lodFile.read()) {
                                        System.out.println("Can't read file (" + aptFile.getFullName() + ")!!");
                                        continue;
                                    }
                                    ssaFile.setApprBytes(lodFile.getApprBytes());
                                    ssaFile.write();
                                }

                                // change the redirector in the APT
                                aptFile.setFullName("appearance/" + file.getName().replace(".apt", ".ssa"));

                                // Create the SSA
                                aptFile.write();
                            }
                            catch (IOException e){
                                System.out.println("ERROR: " + e.getMessage());
                            }
                        }
                    }
                    break;
                case 1:
                    break;
                default:
                    break;
            }
        }
    }
}

package mobilitymodels;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 * @author Tomasz 'TC' Klimek
 */
public class Main {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            for(LookAndFeelInfo lf : UIManager.getInstalledLookAndFeels()) {
                if(lf.getName().equals("Nimbus")) {
                    UIManager.setLookAndFeel(lf.getClassName());
                }
            }
        } catch (Exception ex) { 
        }
        SwingUtilities.invokeLater(new Runnable(){
            
            @Override
            public void run(){
		new Window();
	    }
	});
    }
}

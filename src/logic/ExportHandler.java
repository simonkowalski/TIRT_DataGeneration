package logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import mobilitymodels.Window;

/**
 *
 * @author Kobier
 */
public class ExportHandler {

    private Window parent;
    private MobilityModel currentModel;

    public ExportHandler(Window parent) {
        this.parent = parent;
    }

    public boolean export(MobilityModel model) {
        JFileChooser fc = new JFileChooser();
        int result = fc.showSaveDialog(parent);
        if (result == JFileChooser.APPROVE_OPTION) {
            model.pauseModel();
            File file = fc.getSelectedFile();
            dumpToFile(file, model);
            return true;
        }
        return false;
    }

    private void dumpToFile(File file, MobilityModel model) {
        try {
            try (PrintWriter pw = new PrintWriter(file)) {
                int entityCounter = 0;
                for (Entity entity : model.getEntities()) {
                    for (Step step : entity.getPreviousSteps()) {
                        pw.println(String.format("%d %.2f %.2f %.2f", entityCounter, step.getT(), step.getX(), 
                                step.getY()));
                    }
                    entityCounter++;
                }
            }
            JOptionPane.showMessageDialog(parent, "Export to file " + file.getName() + " completed",
                    "Operation successfull", JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(parent, "File " + file.getName() + " not found, export aborted",
                    "Error occured", JOptionPane.ERROR_MESSAGE);
        }
    }
}

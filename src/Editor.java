import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Editor implements ActionListener {
    JFrame frame;

    JMenuBar menu;

    JMenu file, edit;

    JMenuItem newFile, openFile, saveFile;

    JMenuItem cut, copy, paste, selectAll,close;

    JTextArea textArea;

    Editor(){
        frame = new JFrame();

        menu = new JMenuBar();

        textArea = new JTextArea();

        JPanel panel = new JPanel();

        newFile = new JMenuItem("New File");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save");

        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Exit");

        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);


        file = new JMenu("File");
        edit = new JMenu("Edit");

        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        menu.add(file);
        menu.add(edit);

        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));

        panel.add(textArea,BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(textArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        panel.add(scrollPane);
        frame.add(panel);
        frame.setJMenuBar(menu);
        frame.setBounds(0,0,1100,570);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Editor edit = new Editor();

    }

    // this will be invoked by adding actionlistenrs to the objects
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == newFile){
            new Editor();
        } else if(e.getSource() == openFile){
            JFileChooser fileChooser = new JFileChooser("C:");
            int optionChosen = fileChooser.showOpenDialog(null);
            if(optionChosen == JFileChooser.APPROVE_OPTION){

                File file = fileChooser.getSelectedFile();

                String filePath = file.getPath();

                try{
                    FileReader fileReader = new FileReader(filePath);

                    BufferedReader bufferedReader = new BufferedReader(fileReader);

                    String curr = "", output = "";

                    while((curr = bufferedReader.readLine()) != null){
                        output += curr +"\n";
                    }

                    textArea.setText(output);
                }catch(FileNotFoundException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                } catch(IOException ioException){
                    ioException.printStackTrace();
                }
            }
        } else if(e.getSource() == saveFile){
            JFileChooser fileChooser = new JFileChooser("C:");
            int optionChosen = fileChooser.showSaveDialog(null);
            if(optionChosen == JFileChooser.APPROVE_OPTION){
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");

                try {
                    FileWriter fileWriter = new FileWriter(file);

                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }

        } else if(e.getSource() == cut){
            textArea.cut();
        } else if(e.getSource() == copy){
            textArea.copy();
        } else if(e.getSource() == paste){
            textArea.paste();
        } else if(e.getSource() == selectAll){
            textArea.selectAll();
        } else if(e.getSource() == close){
            System.exit(0);
        }
    }
}
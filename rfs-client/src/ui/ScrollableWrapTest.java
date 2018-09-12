package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Vector;
 
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.filechooser.FileSystemView;
 
public class ScrollableWrapTest {
    public static void main(String[] args) {
        try {
            ScrollableWrapTest st = new ScrollableWrapTest();
            final JPanel mainPanel = new JPanel(new WrapScollableLayout(FlowLayout.LEFT, 10, 10));
            mainPanel.setBackground(Color.WHITE);
            JScrollPane pane = new JScrollPane(mainPanel);
            pane.setPreferredSize(new Dimension(550, 300));
            Vector v = new Vector();
            v.add("first.doc");
            v.add("second.txt");
            v.add("third.pdf");
            v.add("fourth.rar");
            v.add("fifth.zip");
            v.add("sixth.folder");
            v.add("seventh.exe");
            v.add("eighth.bmp");
            v.add("nineth.jpeg");
            v.add("tenth.wav");
            v.add("first.doc");
            v.add("second.txt");
            v.add("third.pdf");
            v.add("fourth.rar");
            v.add("fifth.zip");
            v.add("sixth.folder");
            v.add("seventh.exe");
            v.add("eighth.bmp");
            v.add("nineth.jpeg");
            v.add("tenth.wav");
            v.add("first.doc");
            v.add("second.txt");
            v.add("third.pdf");
            v.add("fourth.rar");
            v.add("fifth.zip");
            v.add("sixth.folder");
            v.add("seventh.exe");
            v.add("eighth.bmp");
            v.add("nineth.jpeg");
            v.add("tenth.wav");
            v.add("first.doc");
            v.add("second.txt");
            v.add("third.pdf");
            v.add("fourth.rar");
            v.add("fifth.zip");
            v.add("sixth.folder");
            v.add("seventh.exe");
            v.add("eighth.bmp");
            v.add("nineth.jpeg");
            v.add("tenth.wav");
           
            JLabel img = null;
            for(int i =0;i<v.size();i++){
                JPanel panel = new JPanel(new BorderLayout());
                String iconPath = st.getIcone(st.getExtension(v.elementAt(i).toString().toLowerCase()));
                        panel.setBackground(Color.WHITE);
                   
                    img = new JLabel();
                    ImageIcon icon = new ImageIcon(iconPath);
                    img.setIcon(icon);
                    img.setBackground(Color.WHITE);
                    
                    JPanel buttonPanel = new JPanel();
                    buttonPanel.setBackground(Color.WHITE);
                    buttonPanel.add(img);
                                       
                    JLabel label = new JLabel((String)v.elementAt(i));
                   
                    label.setHorizontalAlignment(SwingConstants.CENTER);
                    label.setSize(new Dimension(5,5));
                                       
                    panel.add(buttonPanel , BorderLayout.NORTH);
                    panel.add(label, BorderLayout.SOUTH);
                    panel.addMouseListener(new MouseAdapter(){});
                    mainPanel.add(panel);
                    mainPanel.addMouseListener(new MouseAdapter(){
                       
                    });
                mainPanel.revalidate();
        }
         
            st.buildGUI(pane);
           
        } catch (Exception e) {e.printStackTrace();}
    }
    public void buildGUI(JScrollPane scrollPane)
    {
        JFrame frame = new JFrame("Scrollable Wrap Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
      }
    public String getExtension(String name)
        {
                if(name.lastIndexOf(".")!=-1)
                {
                        String extensionPossible = name.substring(name.lastIndexOf(".")+1, name.length());
                        if(extensionPossible.length()>6)
                        {
                                return "";
                        }
                        else
                        {
                                return extensionPossible;
                        }
                }
                else return "";
        }
    
    	public String getIcone(String extension) {
    		
    		File file;
    		String iconName = extension+"-icon-48x48.png";
    		String iconPath = "/home/user/eclipse-workspace/rfs-client/src/ui/images/"+iconName;
    		
//    		this.btnConectar.setIcon(new ImageIcon(this.getClass().getResource("/ui/icons/rss-symbol.png")));
    		return iconPath;
    	}
//        public Icon getIcone(String extension)
//        {
//                File file;
//                String cheminIcone = "";
//                if(((System.getProperties().get("os.name").toString()).startsWith("Mac")))
//                        cheminIcone = System.getProperties().getProperty("file.separator");
//                else if(((System.getProperties().get("os.name").toString()).startsWith("Linux")))
//                        cheminIcone = "/"+"tmp"+"/BoooDrive-"+System.getProperty("user.name")+"/";
//                else cheminIcone = System.getenv("TEMP") + System.getProperties().getProperty("file.separator");
//               
//                File repIcone = new File(cheminIcone);
//                if(!repIcone.exists()) repIcone.mkdirs();
//               
//                try
//                {
//                        if(extension.equals("FOLDER"))
//                        {
//                                file = new File(cheminIcone + "icon");
//                                file.mkdir();
//                        }
// 
// 
//                        else
//                        {
//                                file = new File(cheminIcone + "icon." + extension.toLowerCase());
//                                file.createNewFile();
//                        }
//                       
//                        Icon icone = FileSystemView.getFileSystemView().getSystemIcon(file);
//                       
//                        file.delete();
//                        return icone;
//                }
//                catch (IOException e){ }
//                return null;
//        }
 
    public static class WrapScollableLayout extends FlowLayout {
        public WrapScollableLayout(int align, int hgap, int vgap) {
            super(align, hgap, vgap);
        }
       
        public Dimension preferredLayoutSize(Container target) {
            synchronized (target.getTreeLock()) {
                Dimension dim = super.preferredLayoutSize(target);
                layoutContainer(target);
                int nmembers = target.getComponentCount();
                for (int i = 0 ; i < nmembers ; i++) {
                    Component m = target.getComponent(i);
                    if (m.isVisible()) {
                        Dimension d = m.getPreferredSize();
                        dim.height = Math.max(dim.height, d.height + m.getY());
                    }
                }
                if (target.getParent() instanceof JViewport)
                    dim.width = ((JViewport) target.getParent()).getExtentSize().width;
                Insets insets = target.getInsets();
                dim.height += insets.top + insets.bottom + getVgap();
                return dim;
            }
        }
    }
}

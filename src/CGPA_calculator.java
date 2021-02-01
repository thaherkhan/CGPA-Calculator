package cgpa_calculation;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class CGPA_calculation extends JFrame implements ActionListener {

    private Container c;
    private JLabel l1, l2, l3, l4, head;
    private Font f1, f2;
    private JTextField ft, lt, pt, gt;
    private JButton ab, ub, db, cb, Calculate;
    private JTable ta;
    private DefaultTableModel mo;
    private JScrollPane sp;

    private String[] col = {"Course code", "Course name", "Creade", "GPA"};
    private String[] row = new String[4];

    
    CGPA_calculation() {
        c = getContentPane();
        c.setBackground(Color.YELLOW);
        c.setLayout(null);

        f1 = new Font("Arial", Font.BOLD + Font.ITALIC, 20);
        f2 = new Font("Arial", Font.BOLD, 14);

        head = new JLabel("         INFORMATION");
        head.setBounds(250, 20, 300, 50);
        head.setFont(f1);
        c.add(head);

        l1 = new JLabel("Course code ");
        l1.setBounds(50, 80, 150, 30);
        l1.setFont(f2);
        c.add(l1);

        l2 = new JLabel("Course Title ");
        l2.setBounds(50, 130, 150, 30);
        l2.setFont(f2);
        c.add(l2);

        l3 = new JLabel("Credit     ");
        l3.setBounds(50, 180, 150, 30);
        l3.setFont(f2);
        c.add(l3);

        l4 = new JLabel("GPA    ");
        l4.setBounds(50, 230, 150, 30);
        l4.setFont(f2);
        c.add(l4);

        ft = new JTextField();
        ft.setBounds(150, 80, 250, 30);
        ft.setFont(f2);
        c.add(ft);

        lt = new JTextField();
        lt.setBounds(150, 130, 250, 30);
        lt.setFont(f2);
        c.add(lt);

        pt = new JTextField();
        pt.setBounds(150, 180, 250, 30);
        pt.setFont(f2);
        c.add(pt);

        gt = new JTextField();
        gt.setBounds(150, 230, 250, 30);
        gt.setFont(f2);
        c.add(gt);

        ab = new JButton("Add");
        ab.setBounds(450, 80, 100, 30);
        ab.setFont(f2);
        c.add(ab);

        ub = new JButton("Update");
        ub.setBounds(450, 130, 100, 30);
        ub.setFont(f2);
        c.add(ub);

        db = new JButton("Delete");
        db.setBounds(450, 180, 100, 30);
        db.setFont(f2);
        c.add(db);

        cb = new JButton("Clear");
        cb.setBounds(450, 230, 100, 30);
        cb.setFont(f2);
        c.add(cb);

        Calculate = new JButton("Calculate CGPA");
        Calculate.setBounds(300, 300, 150, 50);
        Calculate.setFont(f2);
        c.add(Calculate);

        ta = new JTable();

        mo = new DefaultTableModel();
        mo.setColumnIdentifiers(col);

        ta.setModel(mo);
        ta.setFont(f2);
        ta.setSelectionBackground(Color.GREEN);
        ta.setBackground(Color.WHITE);
        ta.setRowHeight(30);

//        ta.setEnabled(false);
        sp = new JScrollPane(ta);
        sp.setBounds(10, 360, 740, 265);
        c.add(sp);

        ab.addActionListener(this);
        cb.addActionListener(this);
        db.addActionListener(this);
        ub.addActionListener(this);
        Calculate.addActionListener(this);

        ta.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent m) {
                int numR = ta.getSelectedRow();

                String fn = mo.getValueAt(numR, 0).toString();
                String ln = mo.getValueAt(numR, 1).toString();
                String ph = mo.getValueAt(numR, 2).toString();
                String gpa = mo.getValueAt(numR, 3).toString();

                ft.setText(fn);
                lt.setText(ln);
                pt.setText(ph);
                gt.setText(gpa);

            }
        });

    }
    

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == ab) {

            row[0] = ft.getText();
            row[1] = lt.getText();
            row[2] = pt.getText();
            row[3] = gt.getText();

            mo.addRow(row);

        } else if (e.getSource() == cb) {

            ft.setText("");
            lt.setText("");
            pt.setText("");
            gt.setText("");
        } else if (e.getSource() == db) {

            int numR = ta.getSelectedRow();

            if (numR >= 0) {
                mo.removeRow(numR);
            } else {
                JOptionPane.showMessageDialog(null, "No row has selected");
            }

        } else if (e.getSource() == ub) {

            int numR = ta.getSelectedRow();

            String fn = ft.getText();
            String ln = lt.getText();
            String ph = pt.getText();
            String gpa = gt.getText();

            mo.setValueAt(fn, numR, 0);
            mo.setValueAt(ln, numR, 1);
            mo.setValueAt(ph, numR, 2);
            mo.setValueAt(gpa, numR, 3);

        } else if (e.getSource() == Calculate) {
            
             CGPA();
        }

    }
    
    
    public void CGPA(){
        double Ccreadet = 0, Cnum,Pnum ,total=0;
        int  Ci = 0;
            String Cnum2,Pnum2;
            int Cn= mo.getRowCount();
                   
            while (Ci < Cn) {
                
                Cnum2 = mo.getValueAt(Ci, 2).toString();
                Cnum = Double.parseDouble(Cnum2);
                
                
                Pnum2 = mo.getValueAt(Ci, 3).toString();
                Pnum = Double.parseDouble(Pnum2);
                
                Ccreadet =Ccreadet + Cnum;
                total += Cnum * Pnum;
                
                Ci++; 
            }
            double ans = total/Ccreadet;
            JOptionPane.showMessageDialog(null, "YOUR CGPA is : "+ans);
        
    }
    

    public static void main(String[] args) {

        CGPA_calculation f = new CGPA_calculation();
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setBounds(100, 0, 780, 690);
        f.setTitle("CGPA Counter App");

    }

}
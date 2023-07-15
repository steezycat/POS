


package modsim;


import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;



public class systemcash extends JFrame {
    
    int intquan = 0;
    int totalsales = 0;
    JLabel lblCode = new JLabel("Product Code", JLabel.CENTER);
    JLabel lblName = new JLabel("Product Name", JLabel.CENTER);
    JLabel lblTotal = new JLabel("Total", JLabel.CENTER);
    JLabel lblPay = new JLabel("Pay", JLabel.CENTER);
    JLabel lblBal = new JLabel("Balance", JLabel.CENTER);
    JLabel lblQty = new JLabel("Qty", JLabel.CENTER);
    JLabel lblPrice = new JLabel("Price", JLabel.CENTER);
    JLabel lblAmt = new JLabel("Amount", JLabel.CENTER);
    
    Font font = new Font("Bebas Kai",Font.BOLD, 24);
    SpinnerModel value = new SpinnerNumberModel(1,1,100,1);
    JSpinner qty = new JSpinner(value);
    
    JTextField txtCode = new JTextField();
    JTextField txtName = new JTextField();
    JTextField txtPrice = new JTextField();
    JTextField txtAmt = new JTextField();
    JTextField txtTotal = new JTextField();
    JTextField txtPay = new JTextField();
    JTextField txtBal = new JTextField();
  
    
    JButton btnAdd = new JButton("Add");
    JButton btnEnter = new JButton("Enter");
    JButton btnClear = new JButton("Clear");
    JButton btnPay = new JButton("Pay");
    
    
    JTextArea txtBill = new JTextArea(5,10);

    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();

    JTable table = new JTable();

    String[] columns = {"Product ID","Product Name","Price", "Quantity", "Amount"};
    DefaultTableModel model = new DefaultTableModel();

    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    DefaultTableModel df;
    int total = 0;
   
      public void close(){
        WindowEvent winClose = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClose);
    }

    public void load(){
        Object [] row = new Object[5];
        df = (DefaultTableModel)table.getModel();

        row[0] = txtCode.getText();
        row[1] = txtName.getText();
        row[2] = txtPrice.getText();
        row[3] = qty.getValue();
        row[4] = txtAmt.getText();
        int z = Integer.parseInt(txtAmt.getText());
        total += z;
        String tot = String.valueOf(total);
        txtTotal.setText(tot);
        df.addRow(row);
    }
 

    public systemcash() {
        frame();
    }
    
    public void frame() {

        
        panel1.setBackground(Color.RED);
        panel1.setBounds(20, 50, 550, 150);
        panel1.setLayout(new BorderLayout());

        panel2.setBackground(Color.GREEN);
        panel2.setBounds(600, 300, 250, 350);
        panel2.setLayout(new BorderLayout());

        panel3.setBackground(Color.BLUE);
        panel3.setBounds(20, 230, 550, 450);
        panel3.setLayout(new BorderLayout());

        JFrame f = new JFrame("MEL AND J CANTEEN");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLayout(null);
        f.setSize(900, 750);
        f.getContentPane().setBackground(Color.YELLOW);
        model.setColumnIdentifiers(columns);
        table.setModel(model);

        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.BLACK);
        table.setSelectionBackground(Color.RED);
        table.setGridColor(Color.red);
        table.setSelectionForeground(Color.WHITE);
        table.setFont(new Font("Tahoma", Font.PLAIN, 17));
        table.setRowHeight(30);
        table.setAutoCreateRowSorter(true);

        JScrollPane pane = new JScrollPane(table);
        pane.setForeground(Color.cyan);
        pane.setBackground(Color.orange);
        pane.setBounds(10, 10, 10, 10);

        panel3.add(pane);

        f.setVisible(true);
        lblCode.setBounds(50, 75, 80, 25);
        txtCode.setBounds(50, 110, 80, 25);

        lblName.setBounds(160, 75, 90, 25);
        txtName.setBounds(160, 110, 90, 25);

        lblQty.setBounds(270, 75, 50, 25);

        lblPrice.setBounds(350, 75, 50, 25);
        txtPrice.setBounds(350, 110, 60, 25);
        qty.setBounds(270, 110, 50, 25);
        
      


        lblAmt.setBounds(430, 75, 50, 25);
        txtAmt.setBounds(430, 110, 60, 25);

        btnClear.setBounds(160,150,80,25);
        btnAdd.setBounds(430, 150, 60, 25);
        btnEnter.setBounds(50, 150, 80, 25);
        btnPay.setBounds(700, 10, 80, 25);
        
          
        
        // ACTION PERFORMED FOR ENTER
        btnPay.addActionListener(new ActionListener() {
        	
        	public void actionPerformed(ActionEvent e) {
        		if(txtPay.getText().equals("")) {
        		 JOptionPane.showMessageDialog(null, "Walang nakalagay sa pay!");
        		}
        		
        		int total = Integer.parseInt(txtTotal.getText());
        		int pay = Integer.parseInt(txtPay.getText());
        		
        		if(pay < total) {
        			JOptionPane.showMessageDialog(null, "Insufficient Amount of Payment!");
        		}
        		else {
        		int change = pay - total;
        		String strChng = String.valueOf(change);
        		txtBal.setText(strChng);
        		txtBill.setText("	Mel and J\n"+" **************************************************\n\n"+"Product	Price	Total\n");
        		
        		for(int i = 0; i<df.getRowCount();i++) {
        			Object pname = df.getValueAt(i,1);
        			Object price = df.getValueAt(i,2);
        			Object amount = df.getValueAt(i,4);
        			txtBill.setText(txtBill.getText() + pname.toString() + "\t" + price.toString() + "\t" + amount.toString() + "\n");
        		}
        		
        		txtBill.setText(txtBill.getText() + "\n\n\n\t              Subtotal: "+ txtTotal.getText());
        		txtBill.setText(txtBill.getText() + "\n\t              Pay: "+ txtPay.getText());
        		txtBill.setText(txtBill.getText() + "\n\t              Balance: "+ txtBal.getText());
                        txtBill.setText(txtBill.getText() + "\n\n\n\n    ");
                         txtBill.setText(txtBill.getText() + "***************************************************");
        		}
        		
        	}
        	
  
        });
      
        btnEnter.addActionListener(new ActionListener() {
        		
            public void actionPerformed(ActionEvent e) {
            		
                String pcode = txtCode.getText();
               // int intquan = 0;
               
                qty.setValue(1);
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost/melandj","root","");
                    pst = con.prepareStatement("select * from product where id = ?");
                    pst.setString(1, pcode);
                    rs = pst.executeQuery();

                    if(rs.next() == false) {
                        JOptionPane.showMessageDialog(null, "Product code not found!");


                    }
                    else {
                        String pname = rs.getString("prodname");
                        String price = rs.getString("price");
                        String quan = rs.getString("Quantity");
                        intquan = Integer.parseInt(quan);
                    
                        
                        
                        txtName.setText(pname.trim());
                        txtPrice.setText(price.trim());
                        txtAmt.setText(price);
                        
     
                        
                        qty.addChangeListener(new ChangeListener() {
                       
                			@Override
                			public void stateChanged(ChangeEvent arg0) {
                				// TODO Auto-generated method stub
                				String quantity = qty.getValue().toString();
                				int x = Integer.parseInt(quantity);
                				int y = Integer.parseInt(txtPrice.getText());
                				int amt = x*y;
                				String amount = String.valueOf(amt);
                                             
                				txtAmt.setText(amount);
                        
                			}
                                        
                        });
                           
                    }
      

                } catch (ClassNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });

        // ACTION PERFOMRED FOR ADD
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                String sorderquan = qty.getValue().toString();
                    if (intquan == 0 || intquan <= 0)
                    {
                      JOptionPane.showMessageDialog(null, "No stocks Available!");
                    }
                    else 
                    {
                        int orderquan = Integer.parseInt(sorderquan);
                        totalsales = rs.getInt("totalsales");
                        intquan = intquan - orderquan;
                        totalsales = totalsales + orderquan;
                        String sql = "UPDATE product SET Quantity=?, TotalSales = ? WHERE id = ?";
                         pst = con.prepareStatement(sql);
                         pst.setString(3, txtCode.getText());
                         pst.setString(1, String.valueOf(intquan));
                         pst.setInt(2, totalsales);
                         pst.executeUpdate();
                    }
                } catch (SQLException ex) { 
                    Logger.getLogger(systemcash.class.getName()).log(Level.SEVERE, null, ex);
                }
                load();
                qty.setValue(1);
                txtCode.setText("");
                txtName.setText("");
                txtPrice.setText("");
                txtAmt.setText("");
                
            }
        });
        
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                df = (DefaultTableModel) table.getModel();
                df.setRowCount(0);
                qty.setValue(1);
                txtCode.setText("");
                txtName.setText("");
                txtPrice.setText("");
                txtAmt.setText("");
                txtTotal.setText("");
                
            }
        });
       


        lblTotal.setBounds(675, 70, 50, 25);
        txtTotal.setBounds(630, 105, 155, 25);

        lblPay.setBounds(675, 145, 50, 25);
        txtPay.setBounds(630, 180, 155, 25);

        lblBal.setBounds(675, 220, 50, 25);
        txtBal.setBounds(630, 255, 155, 25);

        txtBill.setEditable(false);
        panel2.add(txtBill);

        f.add(lblCode);
        f.add(txtCode);
        f.add(btnEnter);

        f.add(lblName);
        f.add(txtName);

        f.add(lblQty);
        f.add(qty);

        f.add(lblPrice);
        f.add(txtPrice);

        f.add(lblAmt);
        f.add(txtAmt);
        f.add(btnAdd);

        f.add(lblTotal);
        f.add(txtTotal);

        f.add(lblPay);
        f.add(txtPay);
        f.add(btnPay);
        
       
        f.add(lblBal);
        f.add(txtBal);
        f.add(btnClear);
        f.add(panel1);
        f.add(panel2);
        f.add(panel3);
        
        
    }

    public static void main(String [] Args){
        new systemcash();
    }







    
}

package extra;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;



public class cviews extends JFrame {

        JLabel a , b , result , c ;
        JTextField ta , tb , tresult  ,tc;
        JButton add , sub , mul , div ,per ;

        cviews(){

            setSize(500,500);
            setLayout(null);
            setTitle("calculator");

            a = new JLabel("Enter A");
            a.setBounds(30,30,100,30);
            add(a);
            ta = new JTextField();
            ta.setBounds(140,30,100,30);
            add(ta);
            
            b = new JLabel("Enter B");
            b.setBounds(30,70,100,30);
            add(b);
            tb = new JTextField();
            tb.setBounds(140,70,100,30);    
            add(tb);

            c = new JLabel("Enter c");
            c.setBounds(30,110,100,30);
            add(c);
            tc = new JTextField();
           tc.setBounds(140,110,100,30);
           
            add(tc);

            add = new JButton("Add");
            add.setBounds(30,150,80,30);    
            add(add);
            
            sub = new JButton("Sub");
            sub.setBounds(120,150,80,30);
            add(sub);

            mul = new  JButton("Mul");
            mul.setBounds(210,150,80,30);
            add(mul);
            div = new JButton("Div");
            div.setBounds(300,150,80,30);
            add(div);
        
            result = new JLabel("Result");
            result.setBounds(30,190,100,30);
            add(result);
            tresult = new JTextField();
            tresult.setBounds(140,190,100,30);
            tresult.setEditable(false);
            add(tresult);

            setVisible(true);

        }
    
}

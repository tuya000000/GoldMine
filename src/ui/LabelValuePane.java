/*
 */
package ui;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

/**
 * @author tuya
 */
public class LabelValuePane extends JPanel
{
    private JLabel myLabel = new JLabel();

    private JLabel myValue = new JLabel();

    public LabelValuePane()
    {
        setLayout( new MigLayout( "", "0[]6[]" ) );
        add( myLabel );
        add( myValue );
    }

    public void setLabelText( String text )
    {
        myLabel.setText( text );
    }

    public void setValueText( String text )
    {
        myValue.setText( text );
    }
}

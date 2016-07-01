/**
 * @author ${user}
 *
 * 
 */
package KommunikationEV3;

public interface IKommunikation {

	public void senden(byte[] message);
	public byte[] empfangen();
	public byte[] erzeugeByteArray(int ausgangsknoten, int zielknoten, boolean sensor, boolean aktivierung, boolean powerup, boolean pause);
	public boolean druckSensor();
}

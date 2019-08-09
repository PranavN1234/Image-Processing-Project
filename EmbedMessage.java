private void embedMessage(BufferedImage img, String mess) {
int messageLength = mess.length();
int imageWidth = img.getWidth(), imageHeight = img.getHeight(),
imageSize = imageWidth * imageHeight;
if(messageLength * 8 + 32 > imageSize) {
JOptionPane.showMessageDialog(this, "Message is too long for the chosen image",
"Message too long!", JOptionPane.ERROR_MESSAGE);
return;
}
embedInteger(img, messageLength, 0, 0);
byte b[] = mess.getBytes();
for(int i=0; i<b.length; i++)
embedByte(img, b[i], i*8+32, 0);
}
private void embedInteger(BufferedImage img, int n, int start, int storageBit) {
int maxX = img.getWidth(), maxY = img.getHeight(),
startX = start/maxY, startY = start - startX*maxY, count=0;
for(int i=startX; i<maxX && count<32; i++) {
for(int j=startY; j<maxY && count<32; j++) {
int rgb = img.getRGB(i, j), bit = getBitValue(n, count);
rgb = setBitValue(rgb, storageBit, bit);
img.setRGB(i, j, rgb);
count++;
}
}
}
private void embedByte(BufferedImage img, byte b, int start, int storageBit) {
int maxX = img.getWidth(), maxY = img.getHeight(),
startX = start/maxY, startY = start - startX*maxY, count=0;
for(int i=startX; i<maxX && count<8; i++) {
for(int j=startY; j<maxY && count<8; j++) {
int rgb = img.getRGB(i, j), bit = getBitValue(b, count);
rgb = setBitValue(rgb, storageBit, bit);
img.setRGB(i, j, rgb);
count++;
}
}
}
private int getBitValue(int n, int location) {
 int v = n & (int) Math.round(Math.pow(2, location));
 return v==0?0:1;
 }
private int setBitValue(int n, int location, int bit) {
 int toggle = (int) Math.pow(2, location), bv = getBitValue(n, location);
 if(bv == bit)
 return n;
 if(bv == 0 && bit == 1)
 n |= toggle;
 else if(bv == 1 && bit == 0)
 n ^= toggle;
 return n;
 }
public static void main(String arg[]) {
 new EmbedMessage();
 }
}
//
// begin license header
//
// This file is part of Pixy CMUcam5 or "Pixy" for short
//
// All Pixy source code is provided under the terms of the
// GNU General Public License v2 (http://www.gnu.org/licenses/gpl-2.0.html).
// Those wishing to use Pixy source code, software and/or
// technologies under different licensing terms should contact us at
// cmucam@cs.cmu.edu. Such licensing terms are available for
// all portions of the Pixy codebase presented here.
//
// end license header
//

#include <Pixy2.h>

Pixy2 pixy;
boolean canWrite;

void setup()
{
  Serial.begin(115200);

  pixy.init();
  // change to the line_tracking program.  Note, changeProg can use partial strings, so for example,
  // you can change to the line_tracking program by calling changeProg("line") instead of the whole
  // string changeProg("line_tracking")
  pixy.changeProg("line");
  canWrite = true;
}

void loop()
{
  int8_t i;
  char buf[128];
 
  pixy.line.getMainFeatures();
  if (pixy.line.numVectors) //&& input == "writetome" && canWrite == true) 
  {
  int x1 = pixy.line.vectors->m_x0;
  int x0 = pixy.line.vectors->m_x1;
  int y1 = pixy.line.vectors->m_y0;
  int y0 = pixy.line.vectors->m_y1;

  double changex = x1 - x0;
  double changey = y1 - y0;
  double slope = changey / changex;
  slope = slope * -1;

  if (x1 > x0 - 3 && x1 < x0 + 3)
  {
    if (x1 < 47 && x1 > 37) {
      Serial.println("straight");
    } else if (x1 < 37) {
      Serial.println("strafeLeft");
    } else if (x1 > 47) {
      Serial.println("strafeRight");
    }
  }
  else if (slope > 0) 
  {
    Serial.println("right");
  }
  else if (slope < 0)
  {
    Serial.println("left");
  }
  }
  else 
  {
    Serial.println("notFound");
  }

//Calculate Slope from values above to return positve or negative slope
//based on +/- over serial tell robot to turn left or right

  delay(100);
}

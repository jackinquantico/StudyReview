package com.kh.part_09.abstractPractice.run;

import com.kh.part_09.abstractPractice.model.vo.Animal;
import com.kh.part_09.abstractPractice.model.vo.Cat;
import com.kh.part_09.abstractPractice.model.vo.Dog;

public class AnimalManager {

	public static void main(String[] args) {
		
		Animal[] ani = new Animal[5];
		
		ani[0] = new Cat("°¼¿ËÀÌ", "ÄÚ¼ô", "ÇĞ±³ ±ÙÃ³", "»ï»öÀÌ");
		ani[1] = new Dog("¹ÙµÏÀÌ", "Áøµ¾°³", 6);
		ani[2] = new Dog("¸Û¸ÛÀÌ", "Çã½ºÅ°", 13);
		ani[3] = new Cat("³Ä¿ËÀÌ", "·¢µ¹", "Áı", "ÇÏ¾á»ö");
		ani[4] = new Cat("È÷·Î", "³î½£", "Áı", "°íµî¾î");
		
		for (int i=0; i<ani.length; i++) {
			ani[i].speak();
		}
		
	}

}

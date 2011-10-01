package edu.rutgers.test;

import edu.rutgers.ClickerIdUtil;
import junit.framework.Assert;
import junit.framework.TestCase;

public class ClickerIdUtilTest extends TestCase {

	public void testAlternateIds() {
		Assert.assertEquals("#11111111", ClickerIdUtil.translate("#11111111"));
		Assert.assertEquals("#02222202", ClickerIdUtil.translate("#22222222"));
		//Assert.assertEquals("#33333333", ClickerIdUtil.translate("#33333333"));
		Assert.assertEquals("#04444404", ClickerIdUtil.translate("#44444444"));
		Assert.assertEquals("#55555555", ClickerIdUtil.translate("#55555555"));
		Assert.assertEquals("#66666666", ClickerIdUtil.translate("#66666666"));
		Assert.assertEquals("#77777777", ClickerIdUtil.translate("#77777777"));
		Assert.assertEquals("#08888808", ClickerIdUtil.translate("#88888888"));
		Assert.assertEquals("#99999999", ClickerIdUtil.translate("#99999999"));
		Assert.assertEquals("#00404000", ClickerIdUtil.translate("#40404040"));
		Assert.assertEquals("#005FA8F7", ClickerIdUtil.translate("#405FA8B7"));
		Assert.assertEquals("#00791960", ClickerIdUtil.translate("#40791920"));


		Assert.assertEquals("#10DA3FF5", ClickerIdUtil.translate("#30DA3FD5"));
		Assert.assertEquals("#10EF10EF", ClickerIdUtil.translate("#30EF10CF"));
		Assert.assertEquals("#1116DADD", ClickerIdUtil.translate("#3116DAFD"));

		
	}


	public void testValidIds() {
		// loaner remote
		Assert.assertEquals(true, ClickerIdUtil.isValidClickerId("#1CF48068"));
	
		//Beer,  Michael
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#26274948"));
		//Best,  Rachel
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#186DF683"));
		//Brazaitis,  Rachel
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#0B0F686C"));
		//Bugowski,  Alexander
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#1B371438"));
		//Carbajal,  Silvia
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#26100731"));
		//Castillo Garcia,  Ana
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#0ABAD767"));
		//Castillo,  Danhilda
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#1FDBE226"));
		//Castillo,  Manuel
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#1F621A67"));
		//Chewning,  Robert
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#079B64F8"));
		//Cho,  Moonhyun
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#26A5F370"));
		//Cho,  Moonhyun
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#26A5F370"));
		//Chung,  Justin
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#011AEDF6"));
		//Cohen,  Joseph
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#1BF65BB6"));
		//Connors,  Brian
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#25FAEB34"));
		//Darji,  Hetal
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#1133C2E0"));
		//Deblase,  Alexandra
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#1223DAEB"));
		//Drew,  Daniel
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#00B7A611"));
		//Falasiri,  Chance
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#26164A7A"));
		//Falasiri,  Chance
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#26164A7A"));
		//Fessenden,  Joseph
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#2249B3D8"));
		//Gaylord,  Ashley
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#30EF10CF"));
		//Gillar,  Jamie
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#30DA3FD5"));
		//Gonzalez,  Justin
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#176B364A"));
		//Hall,  Dustin
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#256D7B33"));
		//Hansen,  Kenneth
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#228A49E1"));
		//Heffron,  Evan
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#258F59F3"));
		//Ismail,  Adam
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#260C664C"));
		//Jain,  Priyanka
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#3121B6A6"));
		//Jalil,  Maaz
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#0B0DB2B4"));
		//Kang,  Jennifer
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#0BCE62A7"));
		//Kang,  Julian
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#1FF0A34C"));
		//Katzelnick,  Scott
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#26097F50"));
		//Kern,  Jessica
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#3116DAFD"));
		//Kim,  Gabrielle
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#262CC7CD"));
		//Kim,  Gabrielle
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#262CC7CD"));
		//Kim,  Jun Ho
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#11DE64AB"));
		//Kim,  Samuel
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#08E30BE0"));
		//Kung,  Allen
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#1C8EB123"));
		//Kwon,  Michelle
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#0A9CAD3B"));
		//Kyeremateng,  Faith
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#26704E18"));
		//Kyeremateng,  Faith
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#26704E18"));
		//Le,  Tuan-Anh
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#231D704E"));
		//Lee,  Jeong
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#31C3A95B"));
		//Lizotte,  Ryan
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#06171706"));
		//Lopez,  Diego
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#068546C5"));
		//Lu,  Richard
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#006D503D"));
		//Macdonald,  Kimberly
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#0CE0FA16"));
		//McIvor,  Christopher
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#266B4D00"));
		//McIvor,  Christopher
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#266B4D00"));
		//Mendoza,  Noelle
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#0E42A3EF"));
		//Montero,  Manuel
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#22AD2CA3"));
		//Mukkadan,  Angela
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#121E8884"));
		//Neiman,  Eitan
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#314FB3CD"));
		//Onorato,  Christopher
		//Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#01F50F4B"));
		//Ortuso,  David
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#00C529EC"));
		//Parikh,  Keval
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#06A09137"));
		//Patel,  Neal
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#114E530C"));
		//Patel,  Roshni
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#0AB9982B"));
		//Peguero,  Abraham
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#100C435F"));
		//Rodriguez,  Eileen
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#15287D40"));
		//Roh,  Brian
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#14A161D4"));
		//Salsali,  Vala
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#25752E7E"));
		//Schaeffer,  Christian
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#0CBB1AAD"));
		//Shute,  Alexandra
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#05CA00CF"));
		//Smith,  Andrew
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#083BFCCF"));
		//Soliman,  Andrew
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#079B49D5"));
		//Spike,  Carlett
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#316A0952"));
		//Trombetta,  Jessica
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#0D436628"));
		//Woo,  Nicole
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#1E1AC4C0"));
		//You,  Wenfang
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#26344250"));
		//You,  Wenfang
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#26344250"));
		//Zafar,  Saad
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#0D5F1C4E"));
		//Zakhary,  Mina
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#3173581A"));
		//Zhu,  Jingyan
		Assert.assertEquals(true,ClickerIdUtil.isValidClickerId("#22264642"));
	
	
	
	}


}

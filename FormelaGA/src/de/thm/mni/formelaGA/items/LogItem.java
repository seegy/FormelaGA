package de.thm.mni.formelaGA.items;

import de.thm.mni.formelaGA.FormelItem;

public class LogItem implements TwoCompItem {

	private FormelItem base;
	
	private FormelItem x;
	
	private int depth;
	
	public LogItem(FormelItem base, FormelItem x){
		this.base = base;
		this.x = x;
		depth = (base.getDepth() > x.getDepth()?base.getDepth():x.getDepth())+1;
	}
	
	
	@Override
	public String getWriteAble() {	
		return "log(B "+base.getWriteAble()+")("+x.getWriteAble()+")";
	}

	@Override
	public double getUseCase() {
		
		return Math.log10(x.getUseCase())/Math.log10(base.getUseCase());
	}

	@Override
	public FormelItem mutate() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int getDepth() {
		return depth;
	}


	@Override
	public FormelItem getCompOne() {
		return x;
	}


	@Override
	public FormelItem getCompTwo() {
		return base;
	}

}

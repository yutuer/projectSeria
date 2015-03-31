package com.pureland.common.enums;

public enum MilitaryRank implements BaseEnum {
	MILITARY_RANK_1(new Long(1), ""),
	MILITARY_RANK_2(new Long(2), ""),
	MILITARY_RANK_3(new Long(3), ""),
	MILITARY_RANK_4(new Long(4), ""),
	MILITARY_RANK_5(new Long(5), "");

	private Long id;
	private String code;
	private String name;
	private String label;
	
	
	
	private MilitaryRank(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return this.code;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return this.label;
	}
	
	public static MilitaryRank getMilitaryRankById(Long id) {
		for(MilitaryRank militaryRank : MilitaryRank.values()) {
			if(militaryRank.getId().equals(id)) {
				return militaryRank;
			}
		}
        return null;		
	}

}

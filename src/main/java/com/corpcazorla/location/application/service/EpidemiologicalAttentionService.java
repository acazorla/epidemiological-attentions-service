package com.corpcazorla.location.application.service;

import com.corpcazorla.location.application.model.DataPage;
import com.corpcazorla.location.application.model.SearchAttentionQuery;
import com.corpcazorla.location.domain.model.EpidemiologicalAttention;

public interface EpidemiologicalAttentionService {
	DataPage<EpidemiologicalAttention> listEpidemiologicalAttention(SearchAttentionQuery request);

}

package com.corpcazorla.epidemiological.application.service;

import com.corpcazorla.epidemiological.application.model.DataPage;
import com.corpcazorla.epidemiological.application.model.SearchAttentionQuery;
import com.corpcazorla.epidemiological.domain.model.EpidemiologicalAttention;

public interface EpidemiologicalAttentionService {
	DataPage<EpidemiologicalAttention> listEpidemiologicalAttention(SearchAttentionQuery request);

}

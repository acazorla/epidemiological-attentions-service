package com.corpcazorla.epidemiological.domain.ports.out;

import com.corpcazorla.epidemiological.application.model.DataPage;
import com.corpcazorla.epidemiological.application.model.SearchAttentionQuery;
import com.corpcazorla.epidemiological.domain.model.EpidemiologicalAttention;

public interface EpidemiologicalAttentionRepository {
	DataPage<EpidemiologicalAttention> listEpidemiologicalAttentionRepository(SearchAttentionQuery request);
}

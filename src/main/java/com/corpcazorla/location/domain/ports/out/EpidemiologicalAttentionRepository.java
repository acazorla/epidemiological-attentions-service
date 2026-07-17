package com.corpcazorla.location.domain.ports.out;

import com.corpcazorla.location.application.model.DataPage;
import com.corpcazorla.location.domain.model.EpidemiologicalAttention;
import com.corpcazorla.location.application.model.SearchAttentionQuery;

public interface EpidemiologicalAttentionRepository {
	DataPage<EpidemiologicalAttention> listEpidemiologicalAttentionRepository(SearchAttentionQuery request);
}

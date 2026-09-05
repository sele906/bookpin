package com.sele906.api.library.mapper;

import com.sele906.api.library.domain.Library;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LibraryMapper {
    Library findOne();
    int insertLibrary(Library library);
}

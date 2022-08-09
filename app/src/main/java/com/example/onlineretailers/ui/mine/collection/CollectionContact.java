package com.example.onlineretailers.ui.mine.collection;

import com.example.onlineretailers.roomDatabase.*;
import java.util.List;

public interface CollectionContact {
    interface CollectionPresenter {
        void getCollectionItem();
    }

    interface CollectionView {
        void getCollectionItem(List<Collections> collectionsList);
    }

    interface CollectionModel {
        List<Collections> getCollectionItem();
    }
}

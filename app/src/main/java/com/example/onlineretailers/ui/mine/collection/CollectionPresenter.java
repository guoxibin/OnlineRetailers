package com.example.onlineretailers.ui.mine.collection;

public class CollectionPresenter implements CollectionContact.CollectionPresenter{

    private CollectionModel collectionModel;
    private CollectionContact.CollectionView collectionView;

    public CollectionPresenter(CollectionContact.CollectionView collectionView) {
        this.collectionView = collectionView;
        collectionModel = new CollectionModel();
    }

    @Override
    public void getCollectionItem() {
        collectionView.getCollectionItem(collectionModel.getCollectionItem());
    }
}

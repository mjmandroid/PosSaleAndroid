package com.transpos.sale.ui.scan.mvp;

import com.transpos.sale.base.mvp.BasePresenter;

public class PromptScanPresenter extends BasePresenter<PromptScanContract.Model,PromptScanContract.View> implements PromptScanContract.Presenter{
    @Override
    protected PromptScanContract.Model createModule() {
        return new PromptScanModel();
    }
}

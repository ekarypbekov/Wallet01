package com.wallet.service;


import com.wallet.dao.WalletDao;
import com.wallet.model.Wallet;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/wallets")
public class WalletService {
    private WalletDao walletDao = new WalletDao();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Wallet createWallet(Wallet wallet){
        return walletDao.createWallet(wallet);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Wallet> getAllWallets(){
        return walletDao.getAllWallets();
    }

    @GET
    @Path("/{walletId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Wallet getWalletById(@PathParam("walletId") Long walletId){
        return walletDao.getWalletById(walletId);
    }

}

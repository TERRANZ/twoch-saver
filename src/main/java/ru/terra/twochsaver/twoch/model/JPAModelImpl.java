package ru.terra.twochsaver.twoch.model;

import ru.terra.twochsaver.twoch.db.jpa.TMessage;
import ru.terra.twochsaver.twoch.db.jpa.TThread;
import ru.terra.twochsaver.twoch.db.jpa.controller.TMessageJpaController;
import ru.terra.twochsaver.twoch.db.jpa.controller.TThreadJpaController;
import ru.terra.twochsaver.twoch.dto.PostDTO;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Date: 11.11.13
 * Time: 16:35
 */
public class JPAModelImpl implements Model {
    private TThreadJpaController tpm;
    private TMessageJpaController mpm;

    public static Model getInstance() {
        return new JPAModelImpl();
    }

    private JPAModelImpl() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("2chPU");
        tpm = new TThreadJpaController(emf);
        mpm = new TMessageJpaController(emf);
    }

    @Override
    public void createThread(Long id, long startMessage, String board) {
        tpm.create(new TThread(id, startMessage, board));
    }

    @Override
    public boolean isThreadExists(Long num) {
        return tpm.findByStartMessage(num) != null;
    }

    @Override
    public boolean isMessageExists(Long num) {
        return mpm.isMessageExists(num);
    }

    @Override
    public void createMessage(PostDTO msg) {
        try {
            mpm.create(new TMessage(msg));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {

    }
}

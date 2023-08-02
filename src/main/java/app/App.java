package app;

import shared.infrastructure.MySQLConnection;
import supplier.application.CreateSupplierUseCaseImpl;
import supplier.application.DeleteSupplierUseCaseImpl;
import supplier.application.GetSuppliersUseCaseImpl;
import supplier.application.RetrieveSupplierUseCaseImpl;
import supplier.application.SupplierService;
import supplier.application.UpdateSupplierUseCaseImpl;
import supplier.domain.entities.SupplierRepository;
import supplier.domain.usecases.CreateSupplierUseCase;
import supplier.domain.usecases.DeleteSupplierUseCase;
import supplier.domain.usecases.GetSuppliersUseCase;
import supplier.domain.usecases.RetrieveSupplierUseCase;
import supplier.domain.usecases.UpdateSupplierUseCase;
import supplier.infrastructure.MySQLSupplierRepository;
import user.application.AuthenticateUserUseCaseImpl;
import user.application.RegisterUserUseCaseImpl;
import user.application.UserService;
import user.domain.entities.Session;
import user.domain.entities.UserRepository;
import user.domain.usecases.AuthenticateUserUseCase;
import user.domain.usecases.RegisterUserUseCase;
import user.infrastructure.MySQLUserRepository;

public class App {

    private static UserService userService;
    private static UserRepository userRepository;
    private static AuthenticateUserUseCase authenticateUserUseCase;
    private static RegisterUserUseCase registerUserUseCase;
    private static Session session;

    private static SupplierService supplierService;
    private static SupplierRepository supplierRepository;
    private static CreateSupplierUseCase createSupplierUseCase;
    private static DeleteSupplierUseCase deleteSupplierUseCase;
    private static GetSuppliersUseCase getSuppliersUseCase;
    private static RetrieveSupplierUseCase retrieveSupplierUseCase;
    private static UpdateSupplierUseCase updateSupplierUseCase;

    public static Session session() {
        if (null == session) {
            session = Session.getInstance();
        }
        return session;
    }

    public static UserService userService() {
        if (null == userService) {
            userService = new UserService(authenticateUserUseCase(), registerUserUseCase());
        }

        return userService;
    }

    public static SupplierService supplierService() {
        if (null == supplierService) {
            supplierService = new SupplierService(createSupplierUseCase(), deleteSupplierUseCase(), retrieveSupplierUseCase(), getSuppliersUseCase(), updateSupplierUseCase());
        }

        return supplierService;
    }

    private static UserRepository userRepository() {
        if (null == userRepository) {
            userRepository = new MySQLUserRepository(MySQLConnection.getInstance().getConnection());
        }

        return userRepository;
    }

    private static AuthenticateUserUseCase authenticateUserUseCase() {
        if (null == authenticateUserUseCase) {
            authenticateUserUseCase = new AuthenticateUserUseCaseImpl(userRepository());
        }

        return authenticateUserUseCase;
    }

    private static RegisterUserUseCase registerUserUseCase() {
        if (null == registerUserUseCase) {
            registerUserUseCase = new RegisterUserUseCaseImpl(userRepository());
        }

        return registerUserUseCase;
    }

    private static SupplierRepository supplierRepository() {
        if (null == supplierRepository) {
            supplierRepository = new MySQLSupplierRepository(MySQLConnection.getInstance().getConnection());
        }

        return supplierRepository;
    }

    private static CreateSupplierUseCase createSupplierUseCase() {
        if (null == createSupplierUseCase) {
            createSupplierUseCase = new CreateSupplierUseCaseImpl(supplierRepository());
        }

        return createSupplierUseCase;
    }

    private static DeleteSupplierUseCase deleteSupplierUseCase() {
        if (null == deleteSupplierUseCase) {
            deleteSupplierUseCase = new DeleteSupplierUseCaseImpl(supplierRepository());
        }

        return deleteSupplierUseCase;
    }

    private static RetrieveSupplierUseCase retrieveSupplierUseCase() {
        if (null == retrieveSupplierUseCase) {
            retrieveSupplierUseCase = new RetrieveSupplierUseCaseImpl(supplierRepository());
        }

        return retrieveSupplierUseCase;
    }

    private static GetSuppliersUseCase getSuppliersUseCase() {
        if (null == getSuppliersUseCase) {
            getSuppliersUseCase = new GetSuppliersUseCaseImpl(supplierRepository());
        }

        return getSuppliersUseCase;
    }

    private static UpdateSupplierUseCase updateSupplierUseCase() {
        if (null == updateSupplierUseCase) {
            updateSupplierUseCase = new UpdateSupplierUseCaseImpl(supplierRepository());
        }

        return updateSupplierUseCase;
    }

}

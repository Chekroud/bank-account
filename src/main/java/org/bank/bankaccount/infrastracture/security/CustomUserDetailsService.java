package org.bank.bankaccount.infrastracture.security;

import org.bank.bankaccount.domain.model.Client;
import org.bank.bankaccount.infrastracture.repository.h2.H2DbClientRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Manage User connected
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final H2DbClientRepository clientRepository;

    public CustomUserDetailsService(final H2DbClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Client client = clientRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username or email : " + username));

        return UserPrincipal.create(client);
    }

    public UserDetails getUserConnected() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserDetails) authentication.getPrincipal();
    }
}

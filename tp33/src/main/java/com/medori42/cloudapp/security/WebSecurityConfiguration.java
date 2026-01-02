package com.medori42.cloudapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

/**
 * Configuration de sÃ©curitÃ© web pour l'application cloud.
 *
 * <p>
 * Cette classe de configuration Ã©tablit Spring Security avec le support CORS
 * et permet l'accÃ¨s public aux points d'accÃ¨s API. Elle est conÃ§ue pour
 * les environnements Kubernetes en dÃ©monstration.
 * </p>
 *
 * <p>
 * FonctionnalitÃ©s de sÃ©curitÃ© configurÃ©es:
 * <ul>
 *   <li>Configuration CORS personnalisÃ©e</li>
 *   <li>DÃ©sactivation CSRF pour APIs REST stateless</li>
 *   <li>Autorisation des requÃªtes vers /api/**</li>
 *   <li>Authentification requise pour les autres endpoints</li>
 * </ul>
 * </p>
 *
 * @author 
 * @version 2.0.0
 * @since 2024
 * @see org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

    /**
     * Pattern pour les endpoints API publics.
     */
    private static final String PUBLIC_API_PATTERN = "/api/**";

    /**
     * Pattern pour toutes les routes.
     */
    private static final String ALL_ROUTES_PATTERN = "/**";

    /**
     * Wildcard pour autoriser toutes les origines.
     */
    private static final String ALLOW_ALL_ORIGINS = "*";

    /**
     * Wildcard pour autoriser tous les headers.
     */
    private static final String ALLOW_ALL_HEADERS = "*";

    /**
     * Liste des mÃ©thodes HTTP autorisÃ©es.
     */
    private static final List<String> ALLOWED_HTTP_METHODS = Arrays.asList(
            "GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"
    );

    /**
     * Constructeur par dÃ©faut de la configuration de sÃ©curitÃ©.
     *
     * <p>
     * Initialise la configuration de sÃ©curitÃ© web avec les paramÃ¨tres par dÃ©faut.
     * </p>
     */
    public WebSecurityConfiguration() {
    }

    /**
     * Configure la chaÃ®ne de filtres de sÃ©curitÃ© pour les requÃªtes HTTP.
     *
     * <p>
     * Cette configuration:
     * <ul>
     *   <li>Active CORS avec configuration personnalisÃ©e</li>
     *   <li>DÃ©sactive la protection CSRF (adaptÃ© pour APIs REST stateless)</li>
     *   <li>Autorise toutes les requÃªtes vers /api/** endpoints</li>
     *   <li>Requiert l'authentification pour toutes les autres requÃªtes</li>
     * </ul>
     * </p>
     *
     * @param httpSecurityBuilder le {@link HttpSecurity} Ã  configurer
     * @return la {@link SecurityFilterChain} configurÃ©e
     * @throws Exception si une erreur survient pendant la configuration
     */
    @Bean
    public SecurityFilterChain configureSecurityFilterChain(
            final HttpSecurity httpSecurityBuilder) throws Exception {

        configureCorsSecurity(httpSecurityBuilder);
        configureCsrfProtection(httpSecurityBuilder);
        configureRequestAuthorization(httpSecurityBuilder);

        return httpSecurityBuilder.build();
    }

    /**
     * Configure la sÃ©curitÃ© CORS sur le builder HttpSecurity.
     *
     * @param httpSecurityBuilder le builder de sÃ©curitÃ© HTTP
     * @throws Exception si une erreur survient
     */
    private void configureCorsSecurity(
            final HttpSecurity httpSecurityBuilder) throws Exception {

        httpSecurityBuilder.cors(corsCustomizer ->
            corsCustomizer.configurationSource(createCorsConfigurationSource())
        );
    }

    /**
     * Configure la protection CSRF sur le builder HttpSecurity.
     *
     * @param httpSecurityBuilder le builder de sÃ©curitÃ© HTTP
     * @throws Exception si une erreur survient
     */
    private void configureCsrfProtection(
            final HttpSecurity httpSecurityBuilder) throws Exception {

        httpSecurityBuilder.csrf(AbstractHttpConfigurer::disable);
    }

    /**
     * Configure les autorisations de requÃªtes sur le builder HttpSecurity.
     *
     * @param httpSecurityBuilder le builder de sÃ©curitÃ© HTTP
     * @throws Exception si une erreur survient
     */
    private void configureRequestAuthorization(
            final HttpSecurity httpSecurityBuilder) throws Exception {

        httpSecurityBuilder.authorizeHttpRequests(authorizationConfigurer ->
            authorizationConfigurer
                .requestMatchers(PUBLIC_API_PATTERN).permitAll()
                .anyRequest().authenticated()
        );
    }

    /**
     * Configure les paramÃ¨tres de Cross-Origin Resource Sharing (CORS).
     *
     * <p>
     * Cette configuration autorise:
     * <ul>
     *   <li>Toutes les origines Ã  accÃ©der Ã  l'API</li>
     *   <li>Les mÃ©thodes HTTP courantes (GET, POST, PUT, DELETE, OPTIONS, PATCH)</li>
     *   <li>Tous les headers dans les requÃªtes</li>
     * </ul>
     * </p>
     *
     * <p>
     * <strong>Note:</strong> Dans les environnements de production, considÃ©rez
     * restreindre les origines autorisÃ©es Ã  des domaines spÃ©cifiques pour une
     * sÃ©curitÃ© renforcÃ©e.
     * </p>
     *
     * @return le {@link CorsConfigurationSource} configurÃ©
     */
    @Bean
    public CorsConfigurationSource createCorsConfigurationSource() {
        CorsConfiguration corsConfig = buildCorsConfiguration();

        UrlBasedCorsConfigurationSource corsConfigSource =
            new UrlBasedCorsConfigurationSource();
        corsConfigSource.registerCorsConfiguration(ALL_ROUTES_PATTERN, corsConfig);

        return corsConfigSource;
    }

    /**
     * Construit la configuration CORS avec les paramÃ¨tres dÃ©finis.
     *
     * @return la configuration CORS
     */
    private CorsConfiguration buildCorsConfiguration() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(Arrays.asList(ALLOW_ALL_ORIGINS));
        corsConfig.setAllowedMethods(ALLOWED_HTTP_METHODS);
        corsConfig.setAllowedHeaders(Arrays.asList(ALLOW_ALL_HEADERS));
        return corsConfig;
    }
}

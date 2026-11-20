# Changes in this GitHub-ready version

- Changed Java packages to `com.storeapi` and updated Maven project metadata.
- Removed copied course README, original Git metadata, local IDE files, build outputs, and local `.env`.
- Replaced hardcoded database credentials in `application.yaml` and the Flyway Maven plugin with environment-based settings.
- Corrected product listing so the optional category filter does not default to category 0.
- Corrected the registration `Location` response path.
- Corrected password changes to verify existing BCrypt hashes and hash newly assigned passwords; added request validation.
- Removed a debug print statement and updated README/API examples.

This is a **refactor of a course-based project**, not a clean-room implementation. Permission to redistribute the original starter code remains to be confirmed.

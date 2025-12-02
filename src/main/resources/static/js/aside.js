document.addEventListener('DOMContentLoaded', function () {
    const sidebar = document.querySelector('.sidebar');
    if (!sidebar) return;

    // Create or get the menu-toggle button
    let toggle = document.querySelector('.menu-toggle');
    if (!toggle) {
        toggle = document.createElement('button');
        toggle.className = 'menu-toggle';
        toggle.type = 'button';
        toggle.setAttribute('aria-label', 'Alternar menú');
        toggle.setAttribute('aria-expanded', 'false');
        toggle.innerHTML = '☰';
        document.body.appendChild(toggle);
    }

    // Create or get overlay
    let overlay = document.querySelector('.sidebar-overlay');
    if (!overlay) {
        overlay = document.createElement('div');
        overlay.className = 'sidebar-overlay';
        document.body.appendChild(overlay);
    }

    function openSidebar() {
        sidebar.classList.add('active');
        sidebar.classList.remove('collapsed');
        overlay.classList.add('visible');
        toggle.setAttribute('aria-expanded', 'true');
    }

    function closeSidebar() {
        sidebar.classList.remove('active');
        sidebar.classList.add('collapsed');
        overlay.classList.remove('visible');
        toggle.setAttribute('aria-expanded', 'false');
    }

    // Initialize state based on window width
    function setInitialState() {
        if (window.innerWidth > 850) {
            sidebar.classList.remove('collapsed');
            overlay.classList.remove('visible');
            toggle.setAttribute('aria-expanded', 'true');
        } else {
            sidebar.classList.add('collapsed');
            toggle.setAttribute('aria-expanded', 'false');
        }
    }

    setInitialState();

    // Toggle on button click
    toggle.addEventListener('click', function () {
        if (sidebar.classList.contains('collapsed')) openSidebar();
        else closeSidebar();
    });

    // Close when clicking overlay
    overlay.addEventListener('click', closeSidebar);

    // Close with ESC
    document.addEventListener('keydown', function (e) {
        if (e.key === 'Escape') closeSidebar();
    });

    // Keep behaviour consistent on resize
    let resizeTimeout;
    window.addEventListener('resize', function () {
        clearTimeout(resizeTimeout);
        resizeTimeout = setTimeout(function () {
            if (window.innerWidth > 850) {
                sidebar.classList.remove('collapsed');
                overlay.classList.remove('visible');
                toggle.setAttribute('aria-expanded', 'true');
            } else {
                sidebar.classList.add('collapsed');
                toggle.setAttribute('aria-expanded', 'false');
            }
        }, 120);
    });
});
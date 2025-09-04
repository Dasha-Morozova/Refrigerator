    const $ = (sel) => document.querySelector(sel);
    const api = {
      manufacturers: '/manufacturers',
      refrigerators: '/refrigerators'
    };

    // ===== Производители =====
    async function loadManufacturers() {
      const res = await fetch(api.manufacturers);
      const data = await res.json();
      // таблица
      const tbody = $('#manufacturers-tbody');
      tbody.innerHTML = data.map(m => `
        <tr>
          <td>${m.id}</td>
          <td>${m.name}</td>
          <td>${m.country}</td>
          <td>
            <button data-del-mf="${m.id}">Удалить</button>
          </td>
        </tr>
      `).join('');
      // селект для создания холодильника
      const sel = $('#manufacturer-select');
      sel.innerHTML = data.length
        ? `<option value="" disabled selected>— выберите —</option>` + data.map(m => `<option value="${m.id}">${m.name} (${m.country})</option>`).join('')
        : `<option value="" disabled selected>Сначала создайте производителя</option>`;
    }

    async function createManufacturer(e) {
      e.preventDefault();
      const form = e.target;
      const payload = {
        name: form.name.value.trim(),
        country: form.country.value.trim()
      };
      const res = await fetch(api.manufacturers, {
        method: 'POST',
        headers: {'Content-Type':'application/json'},
        body: JSON.stringify(payload)
      });
      $('#mf-msg').textContent = res.ok ? '✓ Создано' : 'Ошибка создания';
      await loadManufacturers();
      form.reset();
    }

    async function updateManufacturer(e) {
      e.preventDefault();
      const f = e.target;
      const id = Number(f.id.value);
      const payload = { name: f.name.value.trim(), country: f.country.value.trim() };
      const res = await fetch(`${api.manufacturers}/${id}`, {
        method: 'PUT',
        headers: {'Content-Type':'application/json'},
        body: JSON.stringify(payload)
      });
      $('#mf-upd-msg').textContent = res.ok ? '✓ Обновлено' : 'Ошибка обновления';
      await loadManufacturers();
      f.reset();
    }

    async function deleteManufacturer(id) {
      if (!confirm(`Удалить производителя #${id}?`)) return;
      const res = await fetch(`${api.manufacturers}/${id}`, { method: 'DELETE' });
      if (!res.ok) alert('Не удалось удалить (проверьте наличие связанных холодильников).');
      await loadManufacturers();
      await loadRefrigerators();
    }

    // ===== Холодильники =====
    async function loadRefrigerators() {
      const res = await fetch(api.refrigerators);
      const data = await res.json();
      const tbody = $('#refrigerators-tbody');
      tbody.innerHTML = data.map(r => `
        <tr>
          <td>${r.id}</td>
          <td>${r.model}</td>
          <td>${r.type}</td>
          <td>${r.price}</td>
          <td>${r.color}</td>
          <td>${r.length}</td>
          <td>${r.manufacturer ? (r.manufacturer.name + ' (' + r.manufacturer.country + ')') : '—'}</td>
          <td>
            <button data-del-rf="${r.id}">Удалить</button>
          </td>
        </tr>
      `).join('');
    }

    async function createRefrigerator(e) {
      e.preventDefault();
      const f = e.target;
      const manufacturerId = f.manufacturerId.value;
      if (!manufacturerId) { alert('Выберите производителя'); return; }
      const payload = {
        model: f.model.value.trim(),
        type: f.type.value.trim(),
        price: Number(f.price.value),
        color: f.color.value.trim(),
        length: Number(f.length.value)
      };
      const res = await fetch(`${api.refrigerators}/${manufacturerId}`, {
        method: 'POST',
        headers: {'Content-Type':'application/json'},
        body: JSON.stringify(payload)
      });
      $('#rf-msg').textContent = res.ok ? '✓ Создано' : 'Ошибка создания';
      await loadRefrigerators();
      f.reset();
    }

    async function updateRefrigerator(e) {
      e.preventDefault();
      const f = e.target;
      const id = Number(f.id.value);
      const payload = {
        model: f.model.value.trim(),
        type: f.type.value.trim(),
        price: Number(f.price.value),
        color: f.color.value.trim(),
        length: Number(f.length.value)
      };
      const res = await fetch(`${api.refrigerators}/${id}`, {
        method: 'PUT',
        headers: {'Content-Type':'application/json'},
        body: JSON.stringify(payload)
      });
      $('#rf-upd-msg').textContent = res.ok ? '✓ Обновлено' : 'Ошибка обновления';
      await loadRefrigerators();
      f.reset();
    }

    async function deleteRefrigerator(id) {
      if (!confirm(`Удалить холодильник #${id}?`)) return;
      await fetch(`${api.refrigerators}/${id}`, { method: 'DELETE' });
      await loadRefrigerators();
    }

    // ===== Слушатели =====
    document.addEventListener('click', (e) => {
      const delMf = e.target.closest('[data-del-mf]');
      if (delMf) deleteManufacturer(delMf.getAttribute('data-del-mf'));
      const delRf = e.target.closest('[data-del-rf]');
      if (delRf) deleteRefrigerator(delRf.getAttribute('data-del-rf'));
    });

    $('#create-manufacturer-form').addEventListener('submit', createManufacturer);
    $('#update-manufacturer-form').addEventListener('submit', updateManufacturer);
    $('#create-refrigerator-form').addEventListener('submit', createRefrigerator);
    $('#update-refrigerator-form').addEventListener('submit', updateRefrigerator);
    $('#reload-manufacturers').addEventListener('click', loadManufacturers);
    $('#reload-refrigerators').addEventListener('click', loadRefrigerators);

    // Первый рендер
    (async function init() {
      await loadManufacturers();
      await loadRefrigerators();
    })();
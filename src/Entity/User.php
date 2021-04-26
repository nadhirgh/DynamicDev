<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * User
 *
 * @ORM\Table(name="user", uniqueConstraints={@ORM\UniqueConstraint(name="email", columns={"email"})})
 * @ORM\Entity
 */
class User
{
    /**
     * @var int
     *
     * @ORM\Column(name="idUser", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $iduser;

    /**
     * @var string
     *
     * @ORM\Column(name="nom", type="string", length=20, nullable=false)
     */
    private $nom;

    /**
     * @var string
     *
     * @ORM\Column(name="prenom", type="string", length=20, nullable=false)
     */
    private $prenom;

    /**
     * @var string
     *
     * @ORM\Column(name="mdp", type="string", length=255, nullable=false)
     */
    private $mdp;

    /**
     * @var string
     *
     * @ORM\Column(name="email", type="string", length=30, nullable=false)
     */
    private $email;

    /**
     * @var string|null
     *
     * @ORM\Column(name="emailparent", type="string", length=30, nullable=true, options={"default"="NULL"})
     */
    private $emailparent = 'NULL';

    /**
     * @var int|null
     *
     * @ORM\Column(name="numeroparent", type="integer", nullable=true, options={"default"="NULL"})
     */
    private $numeroparent = NULL;

    /**
     * @var string
     *
     * @ORM\Column(name="sexe", type="string", length=10, nullable=false)
     */
    private $sexe;

    /**
     * @var string|null
     *
     * @ORM\Column(name="classe", type="string", length=20, nullable=true, options={"default"="NULL"})
     */
    private $classe = 'NULL';

    /**
     * @var int|null
     *
     * @ORM\Column(name="niveau", type="integer", nullable=true, options={"default"="NULL"})
     */
    private $niveau = NULL;

    /**
     * @var int|null
     *
     * @ORM\Column(name="numero", type="integer", nullable=true, options={"default"="NULL"})
     */
    private $numero = NULL;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="datenaissance", type="date", nullable=false)
     */
    private $datenaissance;

    /**
     * @var string|null
     *
     * @ORM\Column(name="matiere", type="string", length=30, nullable=true, options={"default"="NULL"})
     */
    private $matiere = 'NULL';

    /**
     * @var string
     *
     * @ORM\Column(name="status", type="string", length=20, nullable=false)
     */
    private $status;

    /**
     * @var string|null
     *
     * @ORM\Column(name="role", type="string", length=20, nullable=true, options={"default"="NULL"})
     */
    private $role = 'NULL';

    /**
     * @var string
     *
     * @ORM\Column(name="image", type="string", length=255, nullable=false)
     */
    private $image;


}
